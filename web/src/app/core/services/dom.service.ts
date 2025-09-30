import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Location } from '@angular/common';

// import { ModalOptions } from 'ngx-bootstrap';

@Injectable({
  providedIn: 'root'
})
export class DOMService {

  constructor(
    private router: Router,
    private location: Location
  ) { }

  /*/ Rotas /*/

  /**
   * @description Retorna a página anterior
   */
  voltar(): void {
    this.location.back();
  }

  /**
   * @description Redireciona para a página desejada
   */
  redirecionar(link: string, params?: {}): void {
    this.router.navigate([link], { queryParams: params });
  }

  /**
   * @description Recarrega a página atual
   */
  recarregar(): void {
    location.reload();
  }

  /*/ Animações /*/

  /**
   * @description Desliza a tela até o elemento desejado
   */
  navegarAoElemento(selector: string): void {
    const elemento = document.querySelector(selector);

    if (!!elemento) {
      elemento.scrollIntoView({ behavior: 'smooth' });
    }
  }

  /*/ Funções /*/

  /**
   * @description Abre um link em uma nova guia
   */
  abrirLink(url: string): void {
    window.open(url, '_blank');
  }

  /**
   * @description
   * Faz o download de arquivos dos tipos:
   * - Documentos (.pdf)
   * - Planilhas (.xlsx, .csv)
   * - Imagens (.png, .jpg, .jpeg)
   * @param nomeArquivo - Customize o nome do arquivo
   */
  baixarArquivo(arquivo: Blob, nomeArquivo?: string) {
    let contentType: string = '';

    switch (arquivo.type) {
      case 'application/octet-stream':
        contentType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
        break;
      default:
        contentType = arquivo.type;
        break;
    }

    const blob = new Blob([arquivo], { type: contentType });
    const url = window.URL.createObjectURL(blob);

    const link = document.createElement('a');
    link.download = nomeArquivo || 'Arquivo';
    link.href = url;
    link.click();
  }

}
