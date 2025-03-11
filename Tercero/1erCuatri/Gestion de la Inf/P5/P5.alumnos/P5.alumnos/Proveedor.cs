using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BDLibrary;

namespace P5
{
    public class Proveedor
    {
        private string cif;
        private string nombre;
        private string e_mail;
        private string telefono;

        private static string BD_SERVER = Properties.Settings.Default.BD_SERVER;
        private static string BD_NAME = Properties.Settings.Default.BD_NAME;

        public static List<Proveedor> ListaProveedores()
        {
            // Devuelve una lista con todos los proveedores
            List<Proveedor> lista = new List<Proveedor>();
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);

            foreach (object[] tupla in miBD.Select("SELECT CIF FROM PROVEEDOR"))
            {
                string c = (string)tupla[0];
                Proveedor prov = new Proveedor(c);
                lista.Add(prov);


            }
            return lista;

        }

        public Proveedor(string cif)
        {
            // Crea un Proveedor que ya existe en la base de datos y del que se conoce su CIF
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
            object[] tupla = miBD.Select("SELECT * FROM PROVEEDOR WHERE CIF = '" + cif + "'")[0];
            this.cif = (string)tupla[0];
            this.nombre = (string)tupla[1];
            this.e_mail = (string)tupla[2];
            this.telefono = (string)tupla[3];


        }

        public Proveedor(string cif, string nombre, string e_mail, string telefono)
        {
            // Crea e Inserta un Proveedor que no existe en la base de datos y del que se conocen todos sus atributos
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
            miBD.Insert("INSERT INTO PROVEEDOR VALUES('" + cif + "', '" + nombre + "', '" + e_mail + "', '" + telefono + "')");
            this.cif = cif;
            this.nombre = nombre;
            this.e_mail = e_mail;
            this.telefono = telefono;


        }

        public string CIF
        {
            get { return this.cif; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PROVEEDOR SET CIF = '" + value + "' WHERE CIF = '" + this.cif + "'");
                this.cif = value;

            }
        }

        public string NOMBRE
        {
            get { return this.nombre; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PROVEEDOR SET NOMBRE = '" + value + "' WHERE CIF = '" + this.cif + "'");
                this.nombre = value;

            }
        }

        public string E_MAIL
        {
            get { return this.e_mail; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PROVEEDOR SET E_MAIL = '" + value + "' WHERE CIF = '" + this.cif + "'");
                this.e_mail = value;

            }
        }

        public string TELEFONO
        {
            get { return this.telefono; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PROVEEDOR SET TELEFONO = '" + value + "' WHERE CIF = '" + this.cif + "'");
                this.telefono = value;

            }
        }

        public void Borrar()
        {
            // Borra el Proveedor de la base de datos e invalida sus atributo en memoria
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
            miBD.Delete("DELETE FROM PROVEEDOR WHERE CIF = '" + this.cif + "'");
            this.cif = null;
            this.nombre = null;
            this.e_mail = null;
            this.telefono = null;

        }

        public override string ToString()
        {
            return "(" + this.cif + ") " + this.nombre;
        }

        public override bool Equals(object obj)
        {
            return obj is Proveedor && (((Proveedor)obj).CIF.Equals(this.CIF));
        }

        public override int GetHashCode()
        {
            return this.CIF.GetHashCode();
        }
    }
}
