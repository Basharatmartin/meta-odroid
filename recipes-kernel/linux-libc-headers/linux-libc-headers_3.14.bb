require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

do_install_armmultilib () {
        oe_multilib_header asm/auxvec.h asm/bitsperlong.h asm/byteorder.h asm/fcntl.h asm/hwcap.h asm/ioctls.h asm/kvm.h asm/mman.h asm/param.h
        oe_multilib_header asm/posix_types.h asm/ptrace.h  asm/setup.h  asm/sigcontext.h asm/siginfo.h asm/signal.h asm/stat.h  asm/statfs.h asm/swab.h  asm/types.h asm/unistd.h
}

SRC_URI[md5sum] = "b621207b3f6ecbb67db18b13258f8ea8"
SRC_URI[sha256sum] = "61558aa490855f42b6340d1a1596be47454909629327c49a5e4e10268065dffa"
