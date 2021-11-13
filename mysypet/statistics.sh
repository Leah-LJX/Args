#!/bin/bash
export LC_NUMERIC=C

function build-csv {

    filename="output/time.csv"
    declare -a id
    declare -a soot
    declare -a sketch_gen
    declare -a sketch_comple
    declare -a compilation
    declare -a test_cases
    declare -a total
    declare -a sketch
    declare -a program

    echo "ID,Soot Time,Sketch Generation Time,Sketch Completion Time,Compilation Time,Running Test Cases Time,Total Time,Paths,Programs" > $filename
    for f in {1..30} ; do
        id[$f]=$f
    done

    
    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Soot Time" $z | cut -d ':' -f 2`
	echo$y
            soot[$i]=`echo "scale=2; $y/1000" | bc -l`
	echo ${soot[$i]}
            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Sketch Generation Time" $z | cut -d ':' -f 2`
            sketch_gen[$i]=`echo "scale=2; $y/1000" | bc -l`

            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Sketch Completion Time" $z | cut -d ':' -f 2`
            sketch_comple[$i]=`echo "scale=2; $y/1000" | bc -l`
	
            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Compilation Time" $z | cut -d ':' -f 2`
            compilation[$i]=`echo "scale=2; $y/1000" | bc -l`

            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Running Test cases Time" $z | cut -d ':' -f 2`
            test_cases[$i]=`echo "scale=2; $y/1000" | bc -l`

            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            
            total[$i]=`echo "scale=2; ${soot[$i]} + ${sketch_gen[$i]} + ${sketch_comple[$i]} + ${compilation[$i]} + ${test_cases[$i]}" | bc -l`
	
            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Number of sketches" $z | cut -d ':' -f 2`
            sketch[$i]=`echo "$y" | bc -l`

            (( i++ ))
        done
    done

    i=1
    for f in math geometry joda xml ; do 
        for z in output/$f/*.log ; do
            y=`grep "Number of completed programs" $z | cut -d ':' -f 2`
            program[$i]=`echo "$y" | bc -l`

            (( i++ ))
        done
    done


    echo "ID,Soot Time,Sketch Generation,Sketch Completion,Compilation,Running Test Cases,Total Time,Paths,Programs" > $filename
    for f in {1..30} ; do 
        echo "${id[$f]},${soot[$f]},${sketch_gen[$f]},${sketch_comple[$f]},${compilation[$f]},${test_cases[$f]},${total[$f]},${sketch[$f]},${program[$f]}" >> $filename
    done  

}

build-csv
