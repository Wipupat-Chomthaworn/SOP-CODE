import os
import requests
from bs4 import BeautifulSoup
import re

# Define the URL of the website you want to start scraping from
start_url = 'https://play.pokemonshowdown.com/sprites/'

# Function to clean up a string to make it suitable for use as a folder name
def clean_folder_name(name):
    return re.sub(r'[\/:*?"<>|]', '_', name)

# Function to extract a folder name from a URL
def extract_folder_name(url):
    # Split the URL by "/" and take the last part as the folder name
    parts = url.split("/")
    folder_name = parts[-1]
    # Clean up the folder name to remove invalid characters
    folder_name = clean_folder_name(folder_name)
    return folder_name

# Function to download PNG files from a given URL and organize them into subfolders
def download_and_organize_png_files(url, parent_dir):
    response = requests.get(url)
    
    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')
        
        # Find all tables on the current page
        tables = soup.find_all('table')
        
        for table in tables:
            # Find all <td> elements that contain the specific <a> tag
            td_elements = table.find_all('td', text=re.compile(r'specific text to find in the <a> tag'))
            
            for td in td_elements:
                # Find the <a> tag within the <td> element
                a_tag = td.find('a', href=True)
                if a_tag:
                    png_url = a_tag['href']
                    if not png_url.startswith('http'):
                        png_url = url + '/' + png_url
                    
                    # Get the file name from the URL
                    file_name = os.path.join(parent_dir, os.path.basename(png_url))
                    
                    # Send an HTTP GET request to download the file
                    png_response = requests.get(png_url)
                    
                    if png_response.status_code == 200:
                        with open(file_name, 'wb') as file:
                            file.write(png_response.content)
                    else:
                        print(f"Failed to download {png_url}")
        
    else:
        print(f"Failed to retrieve the web page: {url}")

# Specify a parent directory to save the downloaded files
parent_directory = 'file_pokemon5'
os.makedirs(parent_directory, exist_ok=True)

# Start downloading and organizing PNG files from the starting URL
download_and_organize_png_files(start_url, parent_directory)
