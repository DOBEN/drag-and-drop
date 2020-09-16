cd ./fabric-network/first-network/

#Remove previous Hyperledger Fabric network
echo y | ./byfn.sh down

#Generate the crypto material for a new Hyperledger Fabric network
echo y | ./byfn.sh generate -a -s couchdb -l golang  

#Start the Hyperledger Fabric network
echo y | ./byfn.sh up -a  -s couchdb -l golang  

cd ../../.

if [ -d "wallet/" ]; then
#Remove credentials and wallet of previous Hyperledger Fabric network
rm -r wallet/
fi

#Enroll a new Hyperledger Fabric admin for Organisation 1 and put the credentials into the FileSystemWallet "./website/wallet/"
node enrollAdmin 1
#Register a new Hyperledger Fabric user for Organisation 1 and put the credentials into the FileSystemWallet "./website/wallet/"
node registerUser 1 

