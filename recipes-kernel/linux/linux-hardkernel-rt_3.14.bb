#inherit kernel
#require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

require linux-stable.inc
KBRANCH ?= "odroidc2-3.14.y-rt"
SRCREV ?= "acf6eb161737fcf19dcb689871e782a4b002bb78"

KBRANCH_odroid-c2 ?= "odroidc2-3.14.y-rt"
SRCREV_machine_odroid-c2 ?= "acf6eb161737fcf19dcb689871e782a4b002bb78"

#KBUILD_DEFCONFIG_odroid-c2-rt = "odroidc2rt_defconfig"

LINUX_VERSION = "3.14.79"
KERNEL_DEVICETREE_odroid-c2 = "meson64_odroidc2.dtb"

SRC_URI = "git://github.com/moonlinux/linux-moon.git;branch=${KBRANCH} \
	  "
###https://releases.linaro.org/components/toolchain/binaries/4.9-2017.01/aarch64-linux-gnu/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu.tar.xz;name=aarch64toolchain;subdir=git
SRC_URI += " \
	file://defconfig	\
	file://dtbs.patch	\
	"

##SRC_URI[aarch64toolchain.md5sum] = "631c4c7b1fe9cb115cf51bd6a926acb7"
##SRC_URI[aarch64toolchain.sha256sum] = "d1f2761b697e6b49f5db1ec0cd48d2fd98224be8cb5ef182093f691e99c923eb"
##
##EXTRA_OEMAKE_odroid-c2 = 'V=1 CROSS_COMPILE=${TOOLCHAIN_PREFIX} HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'
##
##LINAROTOOLCHAIN = "4.9.4-2017.01"
##TOOLCHAIN_PREFIX_odroid-c2 = "aarch64-linux-gnu-"
##HOST_PREFIX_odroid-c2 = "${TOOLCHAIN_PREFIX}"
##PATH_prepend ="${S}/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu/bin:"

#PV = "${LINUX_VERSION}+git${SRCPV}"
PV = "${LINUX_VERSION}"

#KCONF_BSP_AUDIT_LEVEL = "0"

do_compile_append() {
	oe_runmake dtbs 
}

inherit deploy

do_deploy_append() {
	install -d ${DEPLOYDIR}
	install ${B}/arch/arm64/boot/dts/meson64_odroidc2.dtb ${DEPLOYDIR}/.
}

COMPATIBLE_MACHINE = "(odroid-c2)"
