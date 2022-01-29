# Do not edit the contents of this file.
all: z_creator z_terminator warnings_OK

warnings_BAD: process_management.c
	gcc -Werror -Wall -g -std=gnu99 -o process_management process_management.c -lrt

warnings_OK: process_management.c
	gcc -Wall -g -std=gnu99 -o process_management process_management.c -lrt

z_creator: z_creator.c
	gcc -Wall -g -std=gnu99  -o z_creator z_creator.c

z_terminator: z_terminator.c
	gcc -Wall -g -std=gnu99  -o z_terminator z_terminator.c
	
runq1: z_creator z_terminator
	./z_terminator

runq2: warnings_OK
	./process_management sample_in.txt | cat output3.txt

clean: *.c
	rm -f process_management z_creator z_terminator
