class=''
method=''
last=''

javatest()
{
	java Performance 1000 ${class} ${method}
}

bashtest()
{
	echo -n "${class} (avg): "
	sleep 0.3
	r1=$(javatest | grep ${last} | awk '{print $2}')
	sleep 0.3
	r2=$(javatest | grep ${last} | awk '{print $2}')
	sleep 0.3
	r3=$(javatest | grep ${last} | awk '{print $2}')
	printf "%.2fms\n" $(bc <<< "scale=2; (${r1} + ${r2} + ${r3}) / 3.0")
}

method='addition'
last='addition'
echo ${last}
class='ArrayList'
bashtest
class='LinkedList'
bashtest
echo

method='addition random_access'
last='random_access'
echo ${last}
class='ArrayList'
bashtest
class='LinkedList'
bashtest
echo

method='addition removal_random'
last='removal_random'
echo ${last}
class='ArrayList'
bashtest
class='LinkedList'
bashtest
echo

method='addition removal_filo'
last='removal_filo'
echo ${last}
class='ArrayList'
bashtest
class='LinkedList'
bashtest
echo

method='addition removal_fifo'
last='removal_fifo'
echo ${last}
class='ArrayList'
bashtest
class='LinkedList'
bashtest
echo
