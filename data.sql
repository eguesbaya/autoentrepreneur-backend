INSERT INTO
  clients (raison_sociale, siren)
VALUES
  ('Language Matters', '012345678'),
  ('WeLocalize', '123456789');

INSERT INTO
  contacts (
    nom,
    prenom,
    telephone,
    email,
    is_contact_principal,
    client_id
  )
VALUES
  (
    'Lara',
    'Jessica',
    '+44 (0)20 7484 8371',
    'jessica@languagematterss.co.uk',
    true,
    1
  ),
  (
    'Krasimira',
    'Herzog',
    '503 807 9771',
    'krasimira.herzog@welocalize.com',
    true,
    2
  ),
  (
    'Cinta',
    'Dominguez',
    '503 807 9771',
    'cinta.dominguez@welocalize.com',
    false,
    2
  );
