/// <reference types="cypress" />

context('Login na aplicação', () => {
  beforeEach(() => {
    cy.visit('http://localhost:4200/entrar')
  })

  it('Testar credenciais inválidas', () => {
    cy.intercept('POST', '/login').as('login')

    cy.get('#apelido').type("usuarioTeste")
    cy.get('#senha').type("fake{enter}")

    // Confirmar erro
    cy.wait('@login').its('response').should('have.property', 'statusCode').should('eq', 401)
    cy.get('.swal2-popup').should('contain.text', 'Credenciais incorretas')
    cy.wait(2000)
  })

  it('Testar credenciais válidas', () => {
    cy.intercept('GET', '/api/usuarios').as('perfil')

    cy.get('#apelido').type("usuarioTeste")
    cy.get('#senha').type("Teste1234{enter}")

    // Confirmar login
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