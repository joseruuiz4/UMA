//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace P6
{
    using System;
    using System.Collections.Generic;
    
    public partial class Producto
    {
        public int ID { get; set; }
        public string NOMBRE { get; set; }
        public string PROVEEDOR { get; set; }
        public float PRECIO { get; set; }
    
        public virtual Proveedor Proveedor1 { get; set; }
    }
}
