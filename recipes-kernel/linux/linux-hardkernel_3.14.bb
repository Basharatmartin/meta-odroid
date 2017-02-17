inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

KBRANCH ?= "odroidc2-3.14.y"
#SRCREV ?= "6ad167426fbad87ff62af517fc01ad9655a89e18"
#SRCREV ?= "1b4fefdf20a84b17d5b666c0686a12a29adcb848"
SRCREV ?= "b22dbcc9173919eaa4bbdeb144abc062f2d5e0fd"

KBRANCH_odroid-c2 ?= "odroidc2-3.14.y"
#SRCREV_machine_odroid-c2 ?= "6ad167426fbad87ff62af517fc01ad9655a89e18"
#SRCREV_machine_odroid-c2 ?= "1b4fefdf20a84b17d5b666c0686a12a29adcb848"
SRCREV_machine_odroid-c2 ?= "b22dbcc9173919eaa4bbdeb144abc062f2d5e0fd"



KERNEL_DEVICETREE_odroid-c2 = "meson64_odroidc2.dtb"

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
