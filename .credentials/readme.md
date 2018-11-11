In this directory, there are two files missing:

- `sonatype-username.cred` -> Contain JUST the Sonatype username
- `sonatype-password.cred` -> Contain JUST the Sonatype password

Those files are used by the publishing script and need to contain your publishing information, according to their name.
Every `.cred` file is ignored by default within the `.GITIGNORE` file.
