# make .bash_profile same as .bashrc
if [ -f "${HOME}/.bashrc" ]; then
    source "${HOME}/.bashrc"
fi
