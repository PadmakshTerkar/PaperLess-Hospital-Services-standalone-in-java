padmaksh@padmaksh:~/projectcode$ su - root

root@padmaksh:~# su - postgres;
postgres@padmaksh:~$ psql tydb;
psql (9.6.4)
	Type "help" for help.

	tydb=# \d
	List of relations
	Schema |       Name       |   Type   |  Owner   
	--------+------------------+----------+----------
	public | demo             | table    | postgres
	public | doctor           | table    | postgres
	public | foo_a_seq        | sequence | postgres
	public | med_pre          | table    | postgres
	public | medicine         | table    | postgres
	public | medicine_mid_seq | sequence | postgres
	public | patient          | table    | postgres
	public | prescription     | table    | postgres
	public | receptionlist    | table    | postgres
(9 rows)

	tydb=# select * from doctor;
	^Z

	postgres@padmaksh:~$ psql tydb;
psql (9.6.4)
	Type "help" for help.

	tydb=# select * from doctor;
	did |  dname   |    dadd    | demail |  specification  | qualification |   dphno    | password 
	-----+----------+------------+--------+-----------------+---------------+------------+----------
	1 | padmaksh | baner,pune |        | heart Specilist | mbbs          | 1451245124 | 
(1 row)

	tydb=# select * from foo_a_seq;
	sequence_name | last_value | start_value | increment_by |      max_value      | min_value | cache_value | log_cnt | is_cycled | is_called 
	---------------+------------+-------------+--------------+---------------------+-----------+-------------+---------+-----------+-----------
	foo_a_seq     |         10 |           1 |            1 | 9223372036854775807 |         1 |           1 |      32 | f         | t
(1 row)

	tydb=# selecr * from med_pre;
ERROR:  syntax error at or near "selecr"
LINE 1: selecr * from med_pre;
^
tydb=# select * from med_pre;
mid | prid 
	-----+------
(0 rows)

	tydb=# select * from medicine;
	mid | mname | mtime 
	-----+-------+-------
(0 rows)

	tydb=# select * from medicine_mid_seq;
	sequence_name   | last_value | start_value | increment_by |      max_value      | min_value | cache_value | log_cnt | is_cycled | is_called 
	------------------+------------+-------------+--------------+---------------------+-----------+-------------+---------+-----------+-----------
	medicine_mid_seq |          1 |           1 |            1 | 9223372036854775807 |         1 |           1 |       0 | f         | f
(1 row)

	tydb=# select * from patient;
	pid | rid |    pname     |               padd               |   pphno    |    pemail     
	-----+-----+--------------+----------------------------------+------------+---------------
	3 |   1 | rushikesk    | sanagavi pune                    |     451245 | abc@gmail.com
	111 |   1 | padmaksh     | vjcvjkcvds                       | 7082564522 | xyz@gmail.com
	123 |   1 | dilip        | pune                             |    4655165 | abc@gmail.com
	2 |   1 | dilip wakale | ra.dastapur,ta-umarga,dist-latur | 9823852556 | xyz@gmail.com
	124 |   1 | p            | mumbai                           |    1425124 | abfgavg
(5 rows)

	tydb=# select * from prescription;
	prid | description | nxt_checkup | prdate | edate | sdate | vsolution | vquery | pid | did 
	------+-------------+-------------+--------+-------+-------+-----------+--------+-----+-----
(0 rows)

	tydb=# select * from receptionlist;
	rid |  rname   | addr |       email        | shift | woff |    phno    |   pwd   
	-----+----------+------+--------------------+-------+------+------------+---------
	1 | d        |      | ewew               |       |      |   23232323 | wewew
	2 | bhjg     |      | a@gmail.com        |       |      |       1245 | 45612
	3 | padmaksh |      | a@gmail.com        |       |      |  412451245 | 1451245
	4 | rishi    |      | dh@gmail.com       |       |      |         99 | 123
	5 | rishi    |      | dh@gmail.com       |       |      |         99 | 123
	6 | ranjeet  |      | a@gmail.cok        |       |      |       1234 | 1234
	7 | a        |      | a                  |       |      |          1 | 1234
	8 | b        |      | asdhgasd@gmail.com |       |      | 1234567989 | 123456
	9 | b        |      | asdhgasd@gmail.com |       |      | 1234567989 | 123456
	10 | dj       |      | d                  |       |      |          2 | 1111
(10 rows)

	tydb=# 

