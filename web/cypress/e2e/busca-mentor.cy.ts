/// <reference types="cypress" />

context('Busca por mentores (filtros)', () => {
  it('Testar busca vazia', () => {
    cy.visit('http://localhost:4200/')

    cy.intercept('GET', '/api/mentores/busca?*').as('pesquisa')
    cy.get('#busca').type("{enter}")
    cy.wait('@pesquisa')

    cy.get('#busca').should('contain.value', '')
    cy.wait(2000)
  })

  it('Testar busca via texto global', () => {
    cy.visit('http://localhost:4200/')

    cy.intercept('GET', '/api/mentores/busca?*').as('pesquisa')
    cy.get('#busca').type("devops{enter}")
    cy.wait('@pesquisa')

    cy.get('#busca').should('contain.value', 'devops')
    cy.wait(2000)
  })

  it('Testar busca via habilidade', () => {
    cy.visit('http://localhost:4200/')

    cy.intercept('GET', '/api/mentores/busca?*').as('pesquisa')
    cy.get('#tag0').click()
    cy.wait('@pesquisa')

    cy.wait(2000)
  })

  it('Testar busca via cargo', () => {
    cy.visit('http://localhost:4200/mentores/busca?pagina=1&totalPorPagina=6')

    cy.intercept('GET', '/api/mentores/busca?*').as('pesquisa')
    cy.get('#cargo').type("professor{enter}")
    cy.wait('@pesquisa')

    cy.get('#cargo').should('contain.value', 'professor')
    cy.wait(2000)
  })

  it('Testar busca via empresa', () => {
    cy.visit('http://localhost:4200/mentores/busca?pagina=1&totalPorPagina=6')

    cy.intercept('GET', '/api/mentores/busca?*').as('pesquisa')
    cy.get('#empresa').type("unifap{enter}")
    cy.wait('@pesquisa')

    cy.get('#empresa').should('contain.value', 'unifap')
    cy.wait(2000)
  })
})