
''' Priyam Banerjee
    UTA ID : 1001529497
    OS Assignment 4
    Local Server-Client interaction. File encryption done using 'pyaes'
    (Python AES Encryption).
    Reference : github.com, stackoverflow.com
'''

#!/usr/bin/env python

import socket
import pyaes
import os
import sys

Socket = socket.socket()
Host = socket.gethostname()
Port = 2000
Socket.bind((Host,Port))
Socket.listen(5)
print("Host Name : ",Host)
print("Server Running....Waiting for client to connect")

#To keep the server running until the client connects

while True:
    conn, socketAddr = Socket.accept()
    print("Incoming connection from : ",socketAddr)
    conn.send(b"Connection established successfully")
    data = conn.recv(4000)
	
    mode = int(conn.recv(4000)) #get operating mode
    if(mode == 1):
     print("\nReady to send file from Server to Client\n")
    elif(mode == 2):
     print("\nReady to send file from Client to Server\n")
    else:
     print("\nIncorrect choice of option/File Name")
     #conn.close()
     break
	
    #FileName = input("What is the file name ?")
    FileName = conn.recv(4000).decode('UTF-8')
    print("File Name : ",FileName)	
    
    if (mode == 1):
     message = b""
     Path = ".\\ServerFiles\\" + FileName
     OpenFile = open(Path,"rb")	
     message = OpenFile.read()
     #print(message)
     conn.send(message)
     #print(message.decode('utf-8'))
     #conn.send(str(message,'utf-8'))
     print("File sent to client successfully !")

    if (mode == 2): #receive file from client
        openConn = open(".\\ServerFiles\\"+FileName,"wb")
        openConn.write(conn.recv(4000))
        openConn.close()
        print("Complete receiving file!\n")

conn.close()