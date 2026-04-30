# Spring Data JPA Queries — Practical Guide

This document summarizes how to design and use queries in Spring Data JPA based on a real-world implementation.

---

## 📌 1. Default Repository Methods (The First Choice)

Spring Data JPA already provides powerful built-in methods through `JpaRepository`.

### Examples

```java
Optional<Ninja> findByEmail(String email);
List<Ninja> findByNameContaining(String name);
Page<Ninja> findAll(Pageable pageable);
```

### ✅ When to use

* Simple queries
* Readable method names
* No need for custom logic

### ❌ Avoid

* Rewriting methods that already exist

Example of what NOT to do:

```java
Page<Ninja> findAllNinjasYeah(Pageable pageable);
```

This is redundant because `findAll(Pageable)` already exists.

---

## 📌 2. Pagination and Sorting

### Service Layer Example

```java
public Page<NinjaResponseDTO> findAllNinjas(int page, int items) {

    Pageable pageable = PageRequest.of(
            page,
            items,
            Sort.by("name").ascending()
                    .and(Sort.by("email").ascending())
    );

    Page<Ninja> ninjasPage = repositoryNinja.findAll(pageable);

    return ninjasPage.map(ninjaMapper::toResponseNinjaDTO);
}
```

### 🔥 Why this is good

* Uses built-in JPA pagination
* Applies sorting cleanly
* Keeps repository simple
* Handles DTO mapping in service

### 🧠 Key Concept

`Page<T>` contains:

* Content (`getContent()`)
* Total pages
* Total elements
* Pagination metadata

---

## 📌 3. When to Use `@Query`

Use `@Query` only when necessary.

### ✅ Good use cases

#### 1. Complex queries

```java
@Query("SELECT n FROM Ninja n WHERE n.age > :age AND n.name LIKE %:name%")
List<Ninja> findAdultNinjasByName(int age, String name);
```

#### 2. Fetch only specific fields (projection optimization)

```java
@Query("SELECT n.email FROM Ninja n")
List<String> findAllEmails();
```

#### 3. JOIN FETCH (performance optimization)

```java
@Query("SELECT n FROM Ninja n JOIN FETCH n.missions")
List<Ninja> findAllWithMissions();
```

#### 4. Native SQL

```java
@Query(value = "SELECT * FROM ninja WHERE age > 18", nativeQuery = true)
List<Ninja> findAdults();
```

---

## 📌 4. When NOT to Use `@Query`

Avoid this:

```java
@Query("SELECT n FROM Ninja n WHERE n.email = :email")
Optional<Ninja> findByEmail(String email);
```

Use instead:

```java
Optional<Ninja> findByEmail(String email);
```

### ❗ Rule

If the method name can express the query → do NOT use `@Query`

---

## 📌 5. Optional vs List vs Page

| Scenario                  | Return Type   |
| ------------------------- | ------------- |
| Unique result (email, ID) | `Optional<T>` |
| Multiple results          | `List<T>`     |
| Multiple + pagination     | `Page<T>`     |

### Example

```java
Optional<Ninja> findByEmail(String email);
```

Why?

* Email should be unique
* Returns 0 or 1 result

---

## 📌 6. Database Constraints (Important)

Always enforce uniqueness at database level:

```java
@Column(unique = true, nullable = false)
private String email;
```

### Why?

* Prevents duplicates
* Ensures consistency
* Avoids hidden bugs

---

## 📌 7. Projections (Alternative to `@Query`)

When you want partial data without JPQL.

### Interface Projection

```java
public interface NinjaEmailView {
    String getEmail();
}
```

### Repository

```java
List<NinjaEmailView> findAllBy();
```

---

## 📌 8. Controller Usage (Important Clarification)

Custom queries work exactly like normal methods.

### Example Flow

#### Repository

```java
List<String> findAllEmails();
```

#### Service

```java
public List<String> getAllEmails() {
    return repository.findAllEmails();
}
```

#### Controller

```java
@GetMapping("/emails")
public ResponseEntity<List<String>> getEmails() {
    return ResponseEntity.ok(service.getAllEmails());
}
```

---

## 📌 9. Design Philosophy (Professional Level)

### Golden Rule

> Use the simplest solution possible. Only increase complexity when necessary.

### Decision Flow

1. Can I use a default method?
   → YES → Use it

2. Is the method name getting too complex?
   → YES → Use `@Query`

3. Do I need performance optimization or joins?
   → YES → Use `@Query`

4. Do I need dynamic filtering?
   → Use `Specification` (advanced)

---

## 📌 10. Common Mistakes

❌ Overusing `@Query`

❌ Creating redundant repository methods

❌ Returning `Page` for unique queries

❌ Ignoring database constraints

❌ Mixing business logic into repository

---

## 📌 Final Summary

* Default JPA methods cover most use cases
* `@Query` is for exceptions, not the rule
* Pagination should stay in service layer
* Repository should stay simple
* Always think about performance and readability

---

## 🚀 Next Steps

* Learn `Specification` for dynamic queries
* Study N+1 problem and `JOIN FETCH`
* Explore DTO projections with constructors
* Add filtering + pagination together

---