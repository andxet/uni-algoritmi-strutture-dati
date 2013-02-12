#!/bin/bash
#for i in $( ls ); do
#	if [${i##*.} == grf] then
#		dot -Tgif $i > ${i##*/}.gif
#	fi
#done
a = 0
for i in $( ls ); do
#	if [${i##*.} == grf] then
		dot -Tgif $i > grafo$a.gif
		$a = $a + 1
done
