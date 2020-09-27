COLLECTION="["

for j in $(echo $1 )
do

COLLECTION_NAME="$j"

COLLECTION=$COLLECTION"{
    \"name\": \"$COLLECTION_NAME\",
    \"policy\": \"${j:0:4}MSP.member', '${j:4:8}MSP.member')\",
    \"requiredPeerCount\": 0,
    \"maxPeerCount\": 3,
    \"blockToLive\":1000000,
    \"memberOnlyRead\": true
 },"

done

COLLECTION=${COLLECTION%?}
COLLECTION=$COLLECTION"]"

echo "$COLLECTION" > /home/doris/Desktop/sequence-diagram/mxgraph/javascript/examples/grapheditor/www/fabric-network/first-network/scripts/collections_config.json