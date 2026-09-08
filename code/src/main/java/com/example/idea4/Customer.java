package com.example.idea4;

import org.springframework.data.annotation.Id;

record Customer(@Id Integer id, String name) {
}