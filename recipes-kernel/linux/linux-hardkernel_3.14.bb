inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"


KERNEL_DEVICETREE_odroid-c2 = "meson64_odroidc2.dtb"

#KBRANCH ?= "odroidc2-3.14.y-rt"
#SRCREV ?= "acf6eb161737fcf19dcb689871e782a4b002bb78"
#KBRANCH_odroid-c2 ?= "odroidc2-3.14.y-rt"
#SRCREV_machine_odroid-c2 ?= "acf6eb161737fcf19dcb689871e782a4b002bb78"
#SRC_URI = "git://github.com/moonlinux/linux-moon.git;branch=${KBRANCH}" 


KBRANCH ?= "odroidc2-3.14.y"
#SRCREV ?= "b22dbcc9173919eaa4bbdeb144abc062f2d5e0fd"
SRCREV ?= "c3e4c730feb1750940971cae9ca1da3ca50f1d56"
BB_GIT_SHALLOW ?= "1"
BB_GIT_SHALLOW_DEPTH ?= "1"

KBRANCH_odroid-c2 ?= "odroidc2-3.14.y"
#SRCREV_machine_odroid-c2 ?= "b22dbcc9173919eaa4bbdeb144abc062f2d5e0fd"
SRCREV_machine_odroid-c2 ?= "c3e4c730feb1750940971cae9ca1da3ca50f1d56"
SRC_URI = "git://github.com/hardkernel/linux.git;branch=${KBRANCH}"


SRC_URI += " \
	file://defconfig	\
	file://dtbs.patch	\
	"

#SRC_URI += " \
#	file://add_uboot.patch \
#	file://0001-compiler-gcc-integrate-the-various-compiler-gcc-345-.patch \
#	file://defconfig"


LINUX_VERSION = "3.14.79"

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONF_BSP_AUDIT_LEVEL = "0"

do_compile_append() {
	oe_runmake dtbs 
}

inherit deploy

do_deploy_append() {
	install -d ${DEPLOYDIR}
	install ${B}/arch/arm64/boot/dts/meson64_odroidc2.dtb ${DEPLOYDIR}/.
}

COMPATIBLE_MACHINE = "(odroid-c2)"
