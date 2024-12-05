#!/bin/bash

# Set Vault address and token environment variables
export VAULT_ADDR="http://127.0.0.1:8200"
export VAULT_TOKEN="YOUR_VAULT_TOKEN"

# Set the Vault secrets path
SECRET_PATH="secret/data/path/to/your-secrets"  # Replace with your Vault path

# Read secrets from Vault using the vault CLI
secrets=$(vault kv get -format=json "$SECRET_PATH")

# Check if secrets were retrieved successfully
if [ -z "$secrets" ] || [ "$secrets" == "null" ]; then
  echo "Failed to retrieve secrets from Vault."
  exit 1
fi

# Parse the secrets and set them as environment variables
echo "$secrets" | jq -r '.data.data | to_entries[] | "\(.key)=\(.value)"' | while IFS= read -r line; do
  key=$(echo "$line" | cut -d '=' -f 1)
  value=$(echo "$line" | cut -d '=' -f 2-)
  export "$key=$value"
  echo "Set environment variable: $key"
done
