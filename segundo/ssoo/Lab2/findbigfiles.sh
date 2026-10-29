#!/bin/bash


if [ ! -d $1 ]
then 
    echo $1 no es un directorio
else
    files=`ls $1`
    for i in $files
    do
        size=`stat $1/$i | grep Size | cut -c 9-12`
        if [ $size -ge $2 ]
        then 
            echo $1/$i: $size bytes
        fi
    done


fi