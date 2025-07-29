/// <reference types="cypress" />

context('Criação de Conta (Aluno)', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/cadastro/aluno')
  })

  it('Testar formulário vazio', () => {
    cy.get('#apelido').type("{enter}")

    // Ler alerta
    cy.get('.swal2-popup').should('contain.text', 'Formulário inválido')
    cy.wait(2000)
  })

  it('Testar e-mail inválido', () => {
    cy.get('#nome').type("Usuário de Teste")
    cy.get('#apelido').type("usuarioTeste")
    cy.get('#email').type("fake")
    cy.get('#senha').type("Teste1234{enter}")

    // Ler alerta
    cy.get('.swal2-popup').should('contain.text', 'Formulário inválido')
    cy.wait(2000)
  })

  it('Testar senha inválida', () => {
    cy.get('#nome').type("Usuário de Teste")
    cy.get('#apelido').type("usuarioTeste")
    cy.get('#email').type("usuarioTeste@example.com")
    cy.get('#senha').type("fake{enter}")

    // Ler alerta
    cy.get('.swal2-popup').should('contain.text', 'Formulário inválido')
    cy.wait(2000)
  })

  it('Testar apelido já cadastrado', () => {
    cy.intercept('POST', '/api/usuarios').as('cadastro')

    cy.get('#nome').type("Usuário de Teste")
    cy.get('#apelido').type("alessfm")
    cy.get('#email').type("usuarioTeste@example.com")
    cy.get('#senha').type("Teste1234{enter}")

    // Confirmar erro
    cy.wait('@cadastro').its('response').should('have.property', 'statusCode').should('eq', 400)
    cy.get('.swal2-popup').should('contain.text', 'Já existe um usuário cadastrado com esse apelido, digite um diferente')
    cy.wait(2000)
  })

  it('Testar credenciais válidas', () => {
    cy.intercept('GET', '/api/usuarios').as('perfil')

    cy.get('#nome').type("Usuário de Teste")
    cy.get('#apelido').type("usuarioTeste")
    cy.get('#email').type("usuarioTeste@example.com")
    cy.get('#senha').type("Teste1234{enter}")

    // Confirmar cadastro e login automático
    cy.get('.swal2-popup').should('contain.text', 'Conta criada com sucesso!')
    cy.wait('@perfil').its('response.body').should('have.property', 'apelido').should('eq', 'usuarioTeste')
    cy.wait(2000)

    // Clicar no botão de perfil no header
    cy.get('.drop')
      .should('be.visible')
      .should('contain.text', 'usuarioTeste')
      .click()
      .contains('Sair').click()

    // Confirmar saída via alerta
    cy.get('.swal2-confirm').click()
  })
})