#!C:\Users\25727\liujiaxin_files\研究生小任务\资料--张银珠学姐\师妹课题\GoogleSearchCrawler-master\GoogleSearchCrawler-master\venv\Scripts\python.exe -x
# EASY-INSTALL-ENTRY-SCRIPT: 'setuptools==39.1.0','console_scripts','easy_install-3.6'
__requires__ = 'setuptools==39.1.0'
import re
import sys
from pkg_resources import load_entry_point

if __name__ == '__main__':
    sys.argv[0] = re.sub(r'(-script\.pyw?|\.exe)?$', '', sys.argv[0])
    sys.exit(
        load_entry_point('setuptools==39.1.0', 'console_scripts', 'easy_install-3.6')()
    )
