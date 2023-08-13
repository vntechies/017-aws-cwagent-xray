package main

import (
	"fmt"
	http "net/http"

	"math/rand"

	cowsay "github.com/Code-Hex/Neo-cowsay/v2"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/dynamodb"
)

type Message struct {
	Id      string `dynamodb:"id"`
	Message string `dynamodb:"message"`
}

// Create function run on EC2 to receive http request then create cowsay message
// Add cowsay message to dynamodb
// Return message to client
func main() {
	fmt.Println("Hello world")
	fmt.Println("Server is running on port 8080")
	fmt.Println("auth aws")
	sess := GetSession()

	handler := func(w http.ResponseWriter, r *http.Request) {
		fromCowSay := getMessage()
		randomId := GenerateRandomString(10)
		w.Write([]byte("Write message to dynamodb\n"))
		w.Write([]byte(randomId + "\n"))
		writeMessage(fromCowSay, sess, randomId, w)
		message, err := readMessage(randomId, sess)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		w.Write([]byte(message))
	}
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}

func getMessage() string {
	hello, err := cowsay.Say("Xin chao", cowsay.Eyes("oO"), cowsay.Tongue("U "), cowsay.Thinking(), cowsay.Random())
	if err != nil {
		fmt.Errorf("Cowsay loi %w", err)
	}
	return hello
}

func writeMessage(messageInput string, sess *session.Session, Id string, w http.ResponseWriter) (*Message, http.ResponseWriter, error) {
	client := dynamodb.New(sess)
	M := Message{Message: messageInput, Id: Id}
	item := map[string]*dynamodb.AttributeValue{
		"id": {
			S: aws.String(M.Id),
		},
		"message": {
			S: aws.String(M.Message),
		},
	}

	// Put the message in DynamoDB.
	putInput := &dynamodb.PutItemInput{
		TableName: aws.String("message"),
		Item:      item,
	}
	_, err := client.PutItem(putInput)
	if err != nil {
		fmt.Println(err)
		return nil, w, fmt.Errorf("Error when put item to dynamodb %w", err)
	}

	return &M, w, nil
}

func GenerateRandomString(length int) string {
	characters := "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	bytes := make([]byte, length)
	for i := range bytes {
		bytes[i] = characters[rand.Intn(len(characters))]
	}
	return string(bytes)
}

func GetSession() *session.Session {
	sess := session.Must(session.NewSession(&aws.Config{Region: aws.String("us-east-1")}))
	return sess
}

func readMessage(id string, sess *session.Session) (string, error) {
	client := dynamodb.New(sess)
	getIn := &dynamodb.GetItemInput{
		TableName: aws.String("message"),
		Key: map[string]*dynamodb.AttributeValue{
			"id": {
				S: aws.String(id),
			},
		},
	}

	// Get the item from DynamoDB.
	result, err := client.GetItem(getIn)
	if err != nil {
		fmt.Println(err)
		return "", fmt.Errorf("Error when get item from dynamodb %w", err)
	}

	// Print the item data.
	if result.Item != nil {
		fmt.Println("Id:", result.Item["id"].S)
		fmt.Println("Message:", result.Item["message"].S)
	} else {
		fmt.Println("Item not found.")
	}
	// return result message
	return *result.Item["message"].S, nil
}
