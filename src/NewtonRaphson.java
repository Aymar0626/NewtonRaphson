import java.util.ArrayList;


public class NewtonRaphson {
    public ArrayList<Iteracion> tabla_iteraciones; // Tabla para las iteraciones
    public boolean converge; // True Converge - False Diverge
    public InterfazEcuacion ecuacion ; // Va a guardar una función dentro la variable

    public NewtonRaphson(int e){
        this.tabla_iteraciones = new ArrayList<>();
        this.converge = false;
        switch (e){
            case 1:
                this.ecuacion = NewtonRaphson::ecuacion1; // Asignamos Funcion Ecuacion1
                break;
            case 2:
                this.ecuacion = NewtonRaphson::ecuacion2; // Asignamos Funcion Ecuacion2
                break;
            case 3:
                this.ecuacion = NewtonRaphson::ecuacion3; // Asignamos Funcion Ecuacion3
                break;
        }
    }

    public double MetodoNewtonRaphson(double xi, double tolerancia, int iteraciones){
        double error;
        double x=0;
        double fxi, fxi_derivada, fx, fx_derivada;


        //Primera aproxicimación , Iniciamos la lista con los valores inciales
        fxi = this.ecuacion.e(xi, false);
        fxi_derivada = this.ecuacion.e(xi, true);
        Iteracion iteracion = new Iteracion(0, xi, fxi, fxi_derivada, 0);
        this.tabla_iteraciones.add(iteracion);

        //Siguientes Iteraciones
        for (int i = 1; i <=iteraciones; i++) {
            Iteracion iteracion_anterior = this.tabla_iteraciones.get(i-1); // Tomamos los datos anteriores de la Lista
            x = iteracion_anterior.x - iteracion_anterior.fx/iteracion_anterior.fx_derivada; // Siguiente Aproximación
            fx = this.ecuacion.e(x, false); // Calculo de funcion de la aproximación
            fx_derivada = this.ecuacion.e(x, true); // Calculo de la derivada de esa función
            error = Math.abs(x-iteracion_anterior.x); // Error es valor absoluto entre el X actual y el X anterior
            iteracion = new Iteracion(i, x, fx, fx_derivada, error); // Creamos el objeto Iteración
            this.tabla_iteraciones.add(iteracion); // Agregamos la iteracion a lista
            //System.out.println(x);
            if(error <= tolerancia) //Si el error es menor que la tolerancia del usuario la función converge
            {
                this.converge=true; // Funcion Converge
                break; // Salimos de las iteraciones
            }
        }

        return x;
    }



    public static Double ecuacion1(double x, boolean derivada){
        if(!derivada)
            return x*Math.exp(Math.cos(x))/1.5 - 1;
        else
            return Math.exp(Math.cos(x))*(1 - x*Math.sin(x)) / 1.5;
    }

    public static Double ecuacion2(double x, boolean derivada){
        if(!derivada)
            return Math.pow(x,2)-Math.sin(x)-2;
        else
            return (2*x)-Math.cos(x);
    }

    public static Double ecuacion3(double x, boolean derivada){
        if(!derivada)
            return Math.pow(x,3)-(1/x)+1;
        else
            return 3*Math.pow(x,2)+(1/Math.pow(x,2));
    }

    public interface InterfazEcuacion{
        Double e(double x, boolean derivada);
    }

}

class Iteracion{
    int iteracion;
    double x, fx, fx_derivada, error;

    public Iteracion(int i, double x, double fx, double fx_derivada, double error)
    {
        this.iteracion = i+1;
        this.x = x;
        this.fx = fx;
        this.fx_derivada = fx_derivada;
        this.error=error;
    }

}
