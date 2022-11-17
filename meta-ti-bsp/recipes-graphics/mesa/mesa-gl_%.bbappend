require ${@bb.utils.contains('MACHINE_FEATURES', 'rogue-gpu', 'rogue', 'vanilla', d)}-mesa.inc
