# import os
# import requests
# from bs4 import BeautifulSoup
# import re

# # Define the URL of the website you want to start scraping from
# start_url = 'https://play.pokemonshowdown.com/sprites/'

# # Function to download PNG files from a given URL and organize them into subfolders
# def download_and_organize_png_files(url, parent_dir):
#     response = requests.get(url)
    
#     if response.status_code == 200:
#         soup = BeautifulSoup(response.text, 'html.parser')
        
#         # Find PNG links on the current page
#         png_links = soup.find_all('a', href=re.compile(r'\.png$'))
        
#         # Find the subnavigation link that corresponds to the current page
#         subnav_link = soup.find('a', href=True)
        
#         if subnav_link:
#             subnav_url = subnav_link['href']
#             if not subnav_url.startswith('http'):
#                 subnav_url = url + '/' + subnav_url
            
#             # Extract the subnavigation link name to create a subfolder
#             subfolder_name = os.path.basename(subnav_url)
            
#             # Create a subdirectory if it doesn't exist
#             subfolder_path = os.path.join(parent_dir, subfolder_name)
#             os.makedirs(subfolder_path, exist_ok=True)
        
#             for link in png_links:
#                 png_url = link['href']
#                 if not png_url.startswith('http'):
#                     png_url = url + '/' + png_url
                
#                 # Get the file name from the URL
#                 file_name = os.path.join(subfolder_path, os.path.basename(png_url))
                
#                 # Send an HTTP GET request to download the file
#                 png_response = requests.get(png_url)
                
#                 if png_response.status_code == 200:
#                     with open(file_name, 'wb') as file:
#                         file.write(png_response.content)
#                 else:
#                     print(f"Failed to download {png_url}")
        
#         # Find links to other pages on the current page
#         page_links = soup.find_all('a', href=True)
        
#         for link in page_links:
#             page_url = link['href']
#             if not page_url.startswith('http'):
#                 page_url = url + '/' + page_url
            
#             # Recursive call to download and organize PNG files from linked pages
#             download_and_organize_png_files(page_url, parent_dir)
#     else:
#         print(f"Failed to retrieve the web page: {url}")

# # Specify a parent directory to save the downloaded files
# parent_directory = 'pokemon'
# os.makedirs(parent_directory, exist_ok=True)

# # Start downloading and organizing PNG files from the starting URL
# download_and_organize_png_files(start_url, parent_directory)

import os
import requests
from bs4 import BeautifulSoup
import re

# Define the URL of the website you want to start scraping from
start_url = 'https://play.pokemonshowdown.com/sprites/'

# Function to clean up a string to make it suitable for use as a folder name
def clean_folder_name(name):
    return re.sub(r'[\/:*?"<>|]', '_', name)

# Function to download PNG files from a given URL and organize them into subfolders
def download_and_organize_png_files(url, parent_dir):
    response = requests.get(url)
    
    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')
        
        # Find PNG links on the current page
        png_links = soup.find_all('a', href=re.compile(r'\.png$'))
        
        # Find the subnavigation link that corresponds to the current page
        subnav_link = soup.find('a', href=True)
        
        if subnav_link:
            subnav_url = subnav_link['href']
            if not subnav_url.startswith('http'):
                subnav_url = url + '/' + subnav_url
            
            # Extract the subnavigation link name to create a subfolder
            subfolder_name = os.path.basename(subnav_url)
            
            # Clean up the subfolder name to remove invalid characters
            subfolder_name = clean_folder_name(subfolder_name)
            
            # Create a subdirectory if it doesn't exist
            subfolder_path = os.path.join(parent_dir, subfolder_name)
            os.makedirs(subfolder_path, exist_ok=True)
        
            for link in png_links:
                png_url = link['href']
                if not png_url.startswith('http'):
                    png_url = url + '/' + png_url
                
                # Get the file name from the URL
                file_name = os.path.join(subfolder_path, os.path.basename(png_url))
                
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
            
            # Recursive call to download and organize PNG files from linked pages
            download_and_organize_png_files(page_url, parent_dir)
    else:
        print(f"Failed to retrieve the web page: {url}")

# Specify a parent directory to save the downloaded files
parent_directory = 'file_pokemon'
os.makedirs(parent_directory, exist_ok=True)

# Start downloading and organizing PNG files from the starting URL
download_and_organize_png_files(start_url, parent_directory)
