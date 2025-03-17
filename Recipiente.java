public class Recipiente implements ServiciosRecipiente{
   
    private double alturaRecipiente;
    private double radio;
    private double areabase;
    private double volumenMaximo;
    private double contenido;
    private static final double PI = Math.PI;

    public Recipiente(double alturaRecipiente, double radio){
        this.alturaRecipiente = alturaRecipiente;
        this.radio = radio;
        this.areabase = Math.PI * Math.pow(this.radio, 2);
        this.volumenMaximo = this.areabase * this.alturaRecipiente;
        this.contenido = 0.0; 
    }

    public double getAltura(){
        return this.alturaRecipiente;
    }
    
    public double capacidad(){
        return this.volumenMaximo;
    }

    public double getContenido(){
        return this.contenido;
    }

    public void setContenido(double nuevoContenido){
        this.contenido = nuevoContenido;
    }

    public double capacidadRestante(){
        return this.volumenMaximo - this.contenido;
    }

    public double getRadio(){
        return this.radio;
    }

    public boolean estaVacio(){
        return (this.contenido == 0);
    }

    public boolean estaLleno(){
        return (this.contenido == this.volumenMaximo);
    }
    
    public double rellena(double cantidad){
        double contenidoProvisional = this.contenido + cantidad;
        if(contenidoProvisional > this.volumenMaximo){
            this.contenido = this.volumenMaximo;
            return contenidoProvisional - this.volumenMaximo;
        } else {
            this.contenido = contenidoProvisional;
            return 0;
        }
    }

    public double vacia(){
        double valorDeAntes = this.contenido;
        this.contenido = 0.0;
        return valorDeAntes;
    }

    public void vierte(Recipiente otro){
        double contenidoProvisional = this.contenido + otro.getContenido();
        if(contenidoProvisional > otro.capacidad()){
            otro.setContenido(otro.capacidad());
            this.contenido = contenidoProvisional - otro.capacidad();
        } else {
            otro.setContenido(contenidoProvisional);
        }
    }

    public boolean mismasDimensiones(Recipiente otro){
        return ((this.radio == otro.getRadio()) && (this.alturaRecipiente == otro.getAltura()));
    }

    public boolean mismaCapacidad(Recipiente otro){
        return (this.capacidad() == otro.capacidad());
    }

    public boolean contieneMas(Recipiente otro){
        return (this.getContenido() > otro.getContenido());
    }

    public boolean cabeMas(Recipiente otro){
        return (this.capacidadRestante() > otro.capacidadRestante());
    }

    public Recipiente creaContenedorJusto(){
        double nuevaAltura = (this.contenido * this.alturaRecipiente) / this.capacidad();
        Recipiente nuevoRecipiente = new Recipiente(nuevaAltura, this.radio);
        return nuevoRecipiente;
    }

    public String muestra() {
        String cadena1 = "////  Especificaciones del recipiente ////\n";
        String cadena2 = "Altura: " + this.alturaRecipiente + " cm\n";
        String cadena3 = "Capacidad: " + this.volumenMaximo + " cm^3\n";      
        String cadena4 = "Radio: " + this.radio + " cm\n"; 
        String cadena5 = "Contenido actual: " + this.contenido + " cm^3\n";
        String cadenaFinal = cadena1 + cadena2 + cadena3 + cadena4 + cadena5 + "------------------------------------\n";
        return cadenaFinal;
     }

    public static void main(String[] args) {
        
        Recipiente recipiente1 = new Recipiente(30, 6);
        Recipiente recipiente2 = new Recipiente(19, 10);
        
        System.out.println("Estado inicial de los recipientes:");
        System.out.println(recipiente1.muestra());
        System.out.println(recipiente2.muestra());

        System.out.println("Rellenando recipiente1 con 2000 cm^3...");
        double sobrante = recipiente1.rellena(600);
        System.out.println("Contenido restante que no cupo: " + sobrante + " cm^3");
        System.out.println(recipiente1.muestra());

        System.out.println("Vaciando recipiente2...");
        double contenidoVaciado = recipiente2.vacia();
        System.out.println("Se vaciaron: " + contenidoVaciado + " cm^3");
        System.out.println(recipiente2.muestra());

        System.out.println("Vertiendo el contenido de recipiente1 en recipiente2...");
        recipiente1.vierte(recipiente2);
        System.out.println("Después de verter:");
        System.out.println(recipiente1.muestra());
        System.out.println(recipiente2.muestra());

        System.out.println("Creando un contenedor justo para el contenido actual de recipiente1...");
        Recipiente recipienteJusto = recipiente1.creaContenedorJusto();
        System.out.println(recipienteJusto.muestra());

        System.out.println("¿Tienen la misma capacidad? " + recipiente1.mismaCapacidad(recipiente2));
        System.out.println("¿Recipiente1 contiene más que recipiente2? " + recipiente1.contieneMas(recipiente2));
        System.out.println("¿Recipiente1 tiene más espacio disponible que recipiente2? " + recipiente1.cabeMas(recipiente2));
    }
}
   


    

