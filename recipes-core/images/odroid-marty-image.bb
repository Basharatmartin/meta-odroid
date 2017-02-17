# Base this image on rpi-hwup-image
#include odroid-hwup-image.bb
include core-image-full-cmdline.bb

IMAGE_INSTALL += " \
	kernel-modules \
        "
#SPLASH = "psplash"
#IMAGE_FEATURES += "splash"

