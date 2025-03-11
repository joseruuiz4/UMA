using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BDLibrary;
using System.Globalization;

namespace P5
{
    public class Producto
    {
        private int id;
        private string nombre;
        private Proveedor proveedor;
        private float precio;

        private static string BD_SERVER = Properties.Settings.Default.BD_SERVER;
        private static string BD_NAME = Properties.Settings.Default.BD_NAME;

        public static List<Producto> ListaProductos()
        {
            // Devuelve una lista con todos los productos
            List<Producto> lista = new List<Producto>();
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);

            foreach (object[] tupla in miBD.Select("SELECT ID FROM Producto"))
            {
                int i = (int)tupla[0];
                Producto p = new Producto(i);
                lista.Add(p);

            }
            return lista;
        }

        public static List<Producto> ListaProductos(Proveedor p)
        {
            // Devuelve una lista con todos los productos de un proveedor
            List<Producto> lista = new List<Producto>();
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
            if (p != null)
            {
                foreach (object[] tupla in miBD.Select("SELECT ID FROM Producto WHERE PROVEEDOR = '" + p.CIF + "'"))
                {
                    int i = (int)tupla[0];
                    Producto prod = new Producto(i);
                    lista.Add(prod);

                }
            }
                return lista;
            
        }

        public Producto(int id)
        {
            // Crea un Producto que ya existe en la base de datos y del que se conoce su ID
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME); 
            object[] tupla = miBD.Select("SELECT * FROM Producto WHERE ID = " + id + "")[0];
            this.id = (int)tupla[0];
            this.nombre = (string)tupla[1];
            this.proveedor = new Proveedor((string)tupla[2]);
            this.precio = (float)tupla[3];



            

        }

        public Producto(string nombre, Proveedor proveedor, float precio)
        {
            // Crea e Inserta un Producto que no existe en la base de datos y del que se conocen todos sus atributos
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);


            miBD.Insert("INSERT INTO PRODUCTO VALUES(" +
                "'" + nombre + "', " +
                "'" + proveedor.CIF + "', " +
                precio.ToString(CultureInfo.InvariantCulture) + ");");


            this.id = (int)(miBD.SelectScalar("SELECT MAX(ID) FROM PRODUCTO;"));
            this.nombre = nombre;
            this.proveedor = proveedor;
            this.precio = precio;

        }

        public int ID
        {
            get { return this.id; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PRODUCTO SET ID = '" + value + "' WHERE ID = " + this.id + ";");
                this.id = value;
            }
        }

        public string NOMBRE
        {
            get { return this.nombre; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PRODUCTO SET NOMBRE = '" + value + "' WHERE ID = " + this.id + ";");
                this.nombre = value;

            }
        }

        public Proveedor PROVEEDOR
        {
            get { return this.proveedor; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PRODUCTO SET PROVEEDOR = '" + value.CIF + "' WHERE ID = " + this.id + ";");
                this.proveedor = value;

            }
        }

        public float PRECIO
        {
            get { return this.precio; }
            set
            {
                // Actualiza el atributo en memoria y en la base de datos
                SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
                miBD.Update("UPDATE PRODUCTO SET PRECIO = '" + value.ToString(CultureInfo.InvariantCulture) + "' WHERE ID = " + this.id + ";");
                this.precio = value;

            }
        }
        public void Borrar()
        {
            // Borra el Producto de la base ed datos e invalida sus atributo en memoria
            SQLSERVERDB miBD = new SQLSERVERDB(BD_SERVER, BD_NAME);
            miBD.Delete("DELETE FROM PRODUCTO WHERE ID = " + this.id + ";");
            this.id = -1;
            this.nombre = null;
            this.proveedor = null;
            this.precio = -1;


        }

        public override string ToString()
        {
            return this.nombre;
        }

        public override bool Equals(object obj)
        {
            return obj is Producto && (((Producto)obj).ID == this.ID);
        }

        public override int GetHashCode()
        {
            return ID.GetHashCode();
        }
    }
}
