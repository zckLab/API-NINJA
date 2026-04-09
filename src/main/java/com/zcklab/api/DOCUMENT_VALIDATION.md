# Document Validation (Java - Bean Validation)

## Overview
Document validation varies by country.
Unlike Brazil, most countries **do not have built-in annotations** like `@CPF`

Common approaches:

- Use built-in annotations when available
- Use `@Pattern` for format validation
- Create custom validations (@Constraint) when needed


## 🇧🇷 Brazil (Hibernate Validator)

### CPF

`@CPF`

private String cpf;

**Format:**

XXX.XXX.XXX-XX
or
XXXXXXXXXXX

---

### CNPJ
`@CNPJ`

private String cnpj;

**Format:**

XX.XXX.XXX/0001-XX

---

# International (No Native Annotation)

🇺🇸 USA — SSN (Social Security Number)

```java
@Pattern(regexp = "\\d{3}-\\d{2}-\\d{4}", message = "Invalid SSN")
private String ssn;
```
**Format:**

XXX-XX-XXXX

---

## 🇪🇸 Spain — DNI

```java
@Pattern(regexp = "\\d{8}[A-Z]", message = "Invalid DNI")
private String dni;
```
**Format:**

12345678A

---

## 🇮🇹 Italy — Codice Fiscale

```java
@Pattern(regexp = "[A-Z0-9]{16}", message = "Invalid Codice Fiscale")
private String codiceFiscale;
```

**Format:**

RSSMRA85M01H501Z

---

## 🇩🇪 Germany — Steuer-ID

```java
@Pattern(regexp = "\\d{11}", message = "Invalid Steuer-ID")
private String steuerId;
```

**Format:**

11 numeric digits

---

# Passport (Generic)

```java
@Pattern(regexp = "[A-Z0-9]{6,9}", message = "Invalid Passport")
private String passport;
```

**Format:**

6 to 9 alphanumeric characters