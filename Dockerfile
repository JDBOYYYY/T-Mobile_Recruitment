FROM jenkins/jenkins:lts

USER root

# Install required packages and Chrome dependencies
RUN apt-get update && \
    apt-get install -y wget unzip libglib2.0-0 libnss3 libgconf-2-4 libfontconfig1 libxss1 libappindicator3-1 libasound2 libatk-bridge2.0-0 libatspi2.0-0 fonts-liberation libdrm2 libgbm1 libu2f-udev libvulkan1 xdg-utils && \
    rm -rf /var/lib/apt/lists/*

# Install Chrome
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg -i google-chrome-stable_current_amd64.deb || apt-get -f install -y && \
    rm google-chrome-stable_current_amd64.deb

# Verify Chrome installation
RUN google-chrome --version

# Install ChromeDriver
RUN wget https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/127.0.6533.72/linux64/chromedriver-linux64.zip && \
    unzip chromedriver-linux64.zip -d /usr/local/bin && \
    mv /usr/local/bin/chromedriver-linux64/chromedriver /usr/local/bin/chromedriver && \
    rm chromedriver-linux64.zip && \
    chmod +x /usr/local/bin/chromedriver

USER jenkins
