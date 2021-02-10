
import socket

# host = socket.gethostname()
host = '127.0.0.1'
port = 8080   # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Connecting')
s.connect((host, port))
print('Connected')
s.sendall(b'{"Hello": "world"}$%$')
print('Sent')
data = s.recv(1024)
print('Received')
s.close()
print('Received', repr(data))
