from splinter import Browser
import time

with Browser() as browser:
    # Visit URL
    url = "http://www.baidu.com"
    browser.visit(url)
    browser.fill('wd', 'splinter - python acceptance testing for web applications')
    # Find and click the 'search' button
    button = browser.find_by_id('su')
    # Interact with elements
    button.click()
    if browser.is_text_present('splinter - python acceptance testing for web applications'):
        print("Yes, the official website was found!")
    else:
        print("No, it wasn't found... We need to improve our SEO techniques")

    time.sleep(5)

#time.sleep(5)
