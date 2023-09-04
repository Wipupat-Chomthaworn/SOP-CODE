# import os
# import requests
# from bs4 import BeautifulSoup
# import re

# # Define the URL of the website you want to scrape
# url = 'https://play.pokemonshowdown.com/sprites/'
# # url = 'https://play.pokemonshowdown.com/'

# # Send an HTTP GET request to the website
# response = requests.get(url)

# # Check if the request was successful
# if response.status_code == 200:
#     # Parse the HTML content of the page
#     soup = BeautifulSoup(response.text, 'html.parser')
    
#     # Find all links with .png in the href attribute
#     png_links = soup.find_all('a', href=re.compile(r'\.png$'))

#     # Specify a directory to save the downloaded files
#     download_dir = 'Pokemon_Sprite_files'
#     os.makedirs(download_dir, exist_ok=True)

#     # Loop through the PNG links and download each file
#     for link in png_links:
#         png_url = link['href']
#         print(png_url)
#         # Make sure the URL is absolute
#         if not png_url.startswith('http'):
#             png_url = url + '/' + png_url
        
#         # Get the file name from the URL
#         file_name = os.path.join(download_dir, os.path.basename(png_url))
        
#         # Send an HTTP GET request to download the file
#         png_response = requests.get(png_url)
        
#         # Check if the request was successful and save the file
#         if png_response.status_code == 200:
#             with open(file_name, 'wb') as file:
#                 file.write(png_response.content)
#         else:
#             print(f"Failed to download {png_url}")
# else:
#     print("Failed to retrieve the web page")



import os
import requests
from bs4 import BeautifulSoup
import re

# Define the URL of the website you want to start scraping from
start_url = 'https://example.com'

# Function to download PNG files from a given URL
def download_png_files(url, download_dir):
    response = requests.get(url)
    
    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')
        
        # Find PNG links on the current page
        png_links = soup.find_all('a', href=re.compile(r'\.png$'))
        
        for link in png_links:
            png_url = link['href']
            if not png_url.startswith('http'):
                png_url = url + '/' + png_url
            
            # Get the file name from the URL
            file_name = os.path.join(download_dir, os.path.basename(png_url))
            
            # Send an HTTP GET request to download the file
            png_response = requests.get(png_url)
            
            if png_response.status_code == 200:
                with open(file_name, 'wb') as file:
                    file.write(png_response.content)
            else:
                print(f"Failed to download {png_url}")
        
        # Find links to other pages on the current page
        page_links = soup.find_all('a', href=True)
        
        for link in page_links:
            page_url = link['href']
            if not page_url.startswith('http'):
                page_url = url + '/' + page_url
            
            # Recursive call to download PNG files from linked pages
            download_png_files(page_url, download_dir)
    else:
        print(f"Failed to retrieve the web page: {url}")

# Specify a directory to save the downloaded files
download_dir = 'downloaded_files'
os.makedirs(download_dir, exist_ok=True)

# Start downloading PNG files from the starting URL
download_png_files(start_url, download_dir)
