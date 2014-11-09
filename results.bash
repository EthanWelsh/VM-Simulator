ECHO GCC OPT
java Main -n 8 -a OPT /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_OPT_8.txt &
java Main -n 16 -a OPT /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_OPT_16.txt &
java Main -n 32 -a OPT /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_OPT_32.txt &
java Main -n 64 -a OPT /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_OPT_64.txt &
java Main -n 128 -a OPT /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_OPT_128.txt &

ECHO BZIP OPT
java Main -n 8 -a OPT /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_OPT_8.txt &
java Main -n 16 -a OPT /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_OPT_16.txt &
java Main -n 32 -a OPT /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_OPT_32.txt &
java Main -n 64 -a OPT /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_OPT_64.txt &
java Main -n 128 -a OPT /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_OPT_128.txt &

ECHO GCC CLOCK
java Main -n 8 -a CLOCK /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_CLOCK_8.txt &
java Main -n 16 -a CLOCK /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_CLOCK_16.txt &
java Main -n 32 -a CLOCK /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_CLOCK_32.txt &
java Main -n 64 -a CLOCK /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_CLOCK_64.txt &
java Main -n 128 -a CLOCK /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_CLOCK_128.txt &

ECHO BZIP CLOCK
java Main -n 8 -a CLOCK /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_CLOCK_8.txt &
java Main -n 16 -a CLOCK /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_CLOCK_16.txt &
java Main -n 32 -a CLOCK /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_CLOCK_32.txt &
java Main -n 64 -a CLOCK /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_CLOCK_64.txt &
java Main -n 128 -a CLOCK /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_CLOCK_128.txt &

ECHO GCC RAND
java Main -n 8 -a RAND /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_RAND_8.txt &
java Main -n 16 -a RAND /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_RAND_16.txt &
java Main -n 32 -a RAND /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_RAND_32.txt &
java Main -n 64 -a RAND /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_RAND_64.txt &
java Main -n 128 -a RAND /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_RAND_128.txt &

ECHO BZIP RAND
java Main -n 8 -a RAND /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_RAND_8.txt &
java Main -n 16 -a RAND /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_RAND_16.txt &
java Main -n 32 -a RAND /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_RAND_32.txt &
java Main -n 64 -a RAND /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_RAND_64.txt &
java Main -n 128 -a RAND /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_RAND_128.txt &

ECHO GCC NRU30
java Main -n 8 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_NRU30_8.txt &
java Main -n 16 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_NRU30_16.txt &
java Main -n 32 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_NRU30_32.txt &
java Main -n 64 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_NRU30_64.txt &
java Main -n 128 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/gcc.trace > gcc_NRU30_128.txt &

ECHO BZIP NRU30
java Main -n 8 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_NRU30_8.txt &
java Main -n 16 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_NRU30_16.txt &
java Main -n 32 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_NRU30_32.txt &
java Main -n 64 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_NRU30_64.txt &
java Main -n 128 -r 30 -a NRU /Users/welshej/github/VM/tracefiles/bzip.trace > bzip_NRU30_128.txt &

