#!/bin/bash
if [ ! -d $1 ] # Chequea si el parámetro de entrada representa un fichero que existe
    then # En caso de no existir, da un mensaje comunicándolo
        echo $1 no existe
    else # En caso de existir el fichero ...
        if [ ! -d $2 ]
        then 
            mkdir $2
        fi

        for i in `ls $1`
        do
            Autor=`cat $1/$i | grep autor | cut -c 7-`
            Titulo=`cat $1/$i | grep titulo | cut -c 8-`
            if [ ! -d $2/$Autor ]
            then    
                mkdir $2/$Autor
            fi
            cp $1/$i $2/$Autor/$Titulo.mp3
        done    
fi
