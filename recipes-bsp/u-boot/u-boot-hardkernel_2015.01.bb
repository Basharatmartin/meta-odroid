DESCRIPTION = "Odroid C2 boot loader supported by the hardkernel product"
SECTION = "bootloaders"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-2015-01:"

PROVIDES = "u-boot"

SRC_URI = " \
	file://boot.ini \
	file://u-boot.bin \
	file://bl1.bin.hardkernel \
	file://uInitrd \
	"

inherit deploy

S = "${WORKDIR}"

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 755  ${S}/boot.ini ${DEPLOYDIR}
    install -m 755  ${S}/bl1.bin.hardkernel ${DEPLOYDIR}
    install -m 755  ${S}/u-boot.bin ${DEPLOYDIR}
    install -m 755  ${S}/uInitrd ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "(odroid-c2|odroid-c2-hardkernel)"
UBOOT_MACHINE_odroid-c2 = "odroidc2_config"
