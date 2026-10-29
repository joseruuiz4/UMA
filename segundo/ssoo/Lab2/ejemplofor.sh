#!/bin/bash
for i in *
do
    if [ -d $i ]
    then    
        
        quees="directorio"
    elif [ -f $i ]
    then
        quees="fichero"
    fi
    echo $i $quees
done