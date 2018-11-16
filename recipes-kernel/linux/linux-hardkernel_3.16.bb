FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

##LINUX_VERSION ?= "3.16.56"
LINUX_VERSION ?= "3.16.60"
KBRANCH ?= "odroidc2-v3.16.y"
##SRCREV ?= "0d11c06623d1db74966bdad3253e0faf9bf274cd"
SRCREV ?= "ea44405d82059a9121ddbdd5b8ea76775d930c7a"
KBUILD_DEFCONFIG_odroid-c2 = "odroidc2_defconfig"

require linux-stable.inc
SRC_URI = "\
	git://github.com/hardkernel/linux;branch=${KBRANCH} \
	https://releases.linaro.org/components/toolchain/binaries/4.9-2017.01/aarch64-linux-gnu/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu.tar.xz;name=aarch64toolchain;subdir=git \
"
SRC_URI += " \
	file://defconfig	\
	"



SRC_URI[aarch64toolchain.md5sum] = "631c4c7b1fe9cb115cf51bd6a926acb7"
SRC_URI[aarch64toolchain.sha256sum] = "d1f2761b697e6b49f5db1ec0cd48d2fd98224be8cb5ef182093f691e99c923eb"

EXTRA_OEMAKE_odroid-c2 = 'V=1 CROSS_COMPILE=${TOOLCHAIN_PREFIX} HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'

LINAROTOOLCHAIN = "4.9.4-2017.01"
TOOLCHAIN_PREFIX_odroid-c2 = "aarch64-linux-gnu-"
HOST_PREFIX_odroid-c2 = "${TOOLCHAIN_PREFIX}"
PATH_prepend ="${S}/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu/bin:"

###KCONF_BSP_AUDIT_LEVEL = "0"
###
do_compile_append() {
	oe_runmake dtbs 
}

inherit deploy

do_deploy_append() {
	install -d ${DEPLOYDIR}
	install ${B}/arch/arm64/boot/dts/meson64_odroidc2.dtb ${DEPLOYDIR}/.
}

# Enable 3.5' TFT Screen
#KERNEL_MODULE_AUTOLOAD_append_odroid-c2 = " fbtft_device flexfb sx865x aml_i2c pwm-meson pwm-ctrl "
#module_conf_fbtft_device = "options fbtft_device name=flexpfb rotate=90"
#module_conf_flexfb = "options flexfb chip=ili9488"
#KERNEL_MODULE_PROBECONF += "fbtft_device flexfb"
COMPATIBLE_MACHINE = "(odroid-c2|odroid-c2-hardkernel)"
