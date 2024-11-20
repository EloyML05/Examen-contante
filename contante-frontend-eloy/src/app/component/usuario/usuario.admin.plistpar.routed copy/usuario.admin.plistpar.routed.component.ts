import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../../service/usuario.service';
import { IUsuario } from '../../../model/usuario.interface';
import { CommonModule } from '@angular/common';
import { IPage } from '../../../model/model.interface';
import { FormsModule } from '@angular/forms';
import { BotoneraService } from '../../../service/botonera.service';
import { debounceTime, Subject } from 'rxjs';
import { Router, RouterModule } from '@angular/router';
import { TrimPipe } from '../../../pipe/trim.pipe';
declare let bootstrap: any;

@Component({
  selector: 'app-usuario.admin.routed',
  templateUrl: './usuario.admin.plistpar.routed.component.html',
  styleUrls: ['./usuario.admin.plistpar.routed.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, TrimPipe, RouterModule],
})

export class UsuarioAdminPlistParRoutedComponent implements OnInit {
  
  myModal: any;

  oPage: IPage<IUsuario> | null = null;
  //
  nPage: number = 0; // 0-based server count
  nRpp: number = 10;
  //
  strField: string = '';
  strDir: string = '';
  //
  strFiltro: string = '';
  //
  arrBotonera: string[] = [];
  //
  private debounceSubject = new Subject<string>();
  constructor(
    private oUsuarioService: UsuarioService,
    private oBotoneraService: BotoneraService,
    private oRouter: Router
  ) {
    this.debounceSubject.pipe(debounceTime(10)).subscribe((value) => {
      this.getPagePar();
    });
  }

  ngOnInit() {
    this.getPagePar();
  }

  getPagePar() {
    this.oUsuarioService
      .getPagePar(this.nPage, this.nRpp, this.strField, this.strDir, this.strFiltro)
      .subscribe({
        next: (oPageFromServer: IPage<IUsuario>) => {
          this.oPage = oPageFromServer;
          this.arrBotonera = this.oBotoneraService.getBotonera(
            this.nPage,
            oPageFromServer.totalPages
          );
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  edit(oUsuario: IUsuario) {
    //navegar a la página de edición
    this.oRouter.navigate(['admin/usuario/edit', oUsuario.id]);
  }

  view(oUsuario: IUsuario) {
    //navegar a la página de edición
    this.oRouter.navigate(['admin/usuario/view', oUsuario.id]);
  }

  remove(oUsuario: IUsuario) {
    this.oRouter.navigate(['admin/usuario/delete/', oUsuario.id]);
  }

  goToPage(p: number) {
    if (p) {
      this.nPage = p - 1;
      this.getPagePar();
    }
    return false;
  }

  goToNext() {
    this.nPage++;
    this.getPagePar();
    return false;
  }

  goToPrev() {
    this.nPage--;
    this.getPagePar();
    return false;
  }

  sort(field: string) {
    this.strField = field;
    this.strDir = this.strDir === 'asc' ? 'desc' : 'asc';
    this.getPagePar();
  }

  goToRpp(nrpp: number) {
    this.nPage = 0;
    this.nRpp = nrpp;
    this.getPagePar();
    return false;
  }

  filter(event: KeyboardEvent) {
    this.debounceSubject.next(this.strFiltro);
  }
create100(){
  this.oUsuarioService.create100().subscribe({
    next:  (data) => {
      this.getPagePar();
      this.showModal('Usuarios totales: ' + data);
    }
  })
  }

  strMessage: string = '';
  showModal(mensaje: string) {
    this.strMessage = mensaje;
    this.myModal = new bootstrap.Modal(document.getElementById('mimodal'), {
      keyboard: false,
    });
    this.myModal.show();
  }
  hideModal = () => {
    this.myModal.hide();
  }

  duplicateCLient( oUsuario: IUsuario) {
    let oUsuarioDuplicate = {} as IUsuario
   oUsuarioDuplicate.nombre = oUsuario.nombre;
   oUsuarioDuplicate.apellido1 = oUsuario.apellido1;
   oUsuarioDuplicate.apellido2 = oUsuario.apellido2;
   oUsuarioDuplicate.email = oUsuario.email;
   oUsuarioDuplicate.id_tipousuario = oUsuario.id_tipousuario;

    this.oUsuarioService.create(oUsuarioDuplicate).subscribe({
      next:  (data) => {
        this.getPagePar();
        this.showModal('Usuario duplicado con el id: ' + data.id);
      }
    })
    }

    duplicateServer( oUsuario: IUsuario){
      this.oUsuarioService.duplicate(oUsuario.id).subscribe({
        next:  (data: IUsuario) => {
          this.getPagePar();
          this.showModal('Usuario duplicado con el id: ' + data.id);
        }
      })
    } 
}

