
''' Priyam Banerjee
    MS Student at University of Texas at Arlington    
    Local Server-Client interaction. File encryption done using 'pyaes'
    (Python AES Encryption).
    Reference : github.com, stackoverflow.com
'''
#!/usr/bin/env python

import socket
import pyaes
import os
import time
import sys


#Setting up connection
Socket = socket.socket()
Host = socket.gethostname()
Port = 2000

Socket.connect((Host,Port))
print(Socket.recv(4000))

mode = input("Options:\n1 : Download file\n2 : Upload file\n==>")
FileName = input("Input the File name :  ")
opMode = int(mode)
if(opMode == 1):
    try:
        File = open("C:\\Users\\priya\\ServerFiles\\" + FileName, "r")
    except "IOError":
        print("Filename / Pathname is incorrect")
        Socket.close()
elif(opMode == 2):
    try:
        File = open("C:\\Users\\priya\\ClientFiles\\" + FileName, "r")
    except "IOError":
        print("Filename / Pathname is incorrect")
        Socket.close()
else:
    print("Incorrect choice of option ! Exiting Client")
    Socket.close()
Socket.send(b'Client connected!') #message to server
Socket.send(bytes(mode, "UTF-8"))

#Using user encryption key
userKey = input("Encrypt key : ")
while (not(len(userKey) == 16 or len(userKey) == 24 or len(userKey)== 32)):
                userKey+='a'
#enc = pyaes.AESModeOfOperationCTR(str.encode(userKey)) #encryption from github...
'''enc = pyaes.AESModeOfOperationCTR(str.encode(userKey))
plaintext = "This_key_for_demo_purpose"
ciphertext = enc.encrypt(plaintext)
print("CIPHERTEXT",ciphertext)
enc = pyaes.AESModeOfOperationCTR(str.encode(userKey))
decrypted = enc.decrypt(ciphertext)
print("DECRYPTED TEXT : ",decrypted) '''
Socket.send(bytes(FileName,"UTF-8"))

mode = int(mode)
if(mode == 1):

    File = open(".\\ClientFiles\\" + FileName, "wb")
    sender = Socket.recv(4000) #receive file from server
    enc = pyaes.AESModeOfOperationCTR(str.encode(userKey))
    #print("SENDER VAR :",sender)
    cipherText = enc.encrypt(sender) 
    #File.write(cipherText)
    enc = pyaes.AESModeOfOperationCTR(str.encode(userKey))
    dec = enc.decrypt(cipherText) 
    #print("DECRYPTION : ",dec)
    File.write(dec)
    #File.write(dm) #Write to file
    File.close()
    print("Download completed to Client Side")
elif(mode == 2):
   File = open(".\\ClientFiles\\" + FileName, "rb")
   Send = File.read() #read file
   enc = pyaes.AESModeOfOperationCTR(str.encode(userKey))
   cipherText = enc.encrypt(Send)
   print("CIPHERTEXT : ",cipherText)
   writeFile = open(".\\ServerFiles\\" + FileName, "wb")
   #writeFile.write(cipherText)
   enc = pyaes.AESModeOfOperationCTR(str.encode(userKey))
   dec = enc.decrypt(cipherText)
   #print("DECRYPTION : ",dec)
   writeFile.write(dec)
   writeFile.close()
   File.close()

   #EM = encrypt.encrypt(Send) #Encrypts
   #datas = encrypt.decrypt(EM)
   #print(datas)
   #Socket.send(EM) #send data to server
   #print("Upload completed")
else:
   print("Incorrect choice of input option")
   Socket.close()

Socket.close()
