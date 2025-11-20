#!/bin/bash

echo "=== API de Investimentos Caixa ==="
echo ""
echo "Construindo o projeto..."
mvn clean install -q

if [ $? -eq 0 ]; then
    echo "Build concluído com sucesso!"
    echo ""
    echo "Iniciando aplicação..."
    echo "API disponível em: http://localhost:8080"
    echo ""
    mvn spring-boot:run
else
    echo "Erro no build do projeto"
    exit 1
fi
