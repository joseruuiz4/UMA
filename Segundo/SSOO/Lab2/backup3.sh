#!/bin/bash
dir=~/ssoo/BACKUP
for i in $@
do
    if [ ! -f "$i" ] # Chequea si el parámetro de entrada representa un fichero que existe
    then # En caso de no existir, da un mensaje comunicándolo
        echo $1 no existe
    else # En caso de existir el fichero ...
        A=$(ls $i* | wc -w) # Lista los ficheros que comienzan por el nombre dado como
                            # parámetro y cuenta cuantos hay y lo almacena en A
        if [ $A -ge 9 ] # Si hay 9 o más, da un mensaje indicando que se ha
                        # alcanzado el máximo número de versiones
        then
            echo “Se ha superado el número máximo de versiones”
        else # Si no se ha alcanzado el maximo ...
            Num=$(date "+%y%m%d")
            mkdir ~/ssoo/BACKUP/$Num
            cp  "$i" $dir/$Num/
        fi
    fi
done