<h1 align="center">🌟 Application Bancaire </h1>

<h2>📋 Table des Matieres</h2>

- [🔍 Presentation](#-about)
- [🏛️ Architecture](#-architecture)
- [🚀 Microservices](#-microservices)

## 🔍 Presentation

<p>
    L'application bancaire est construite à l'aide d'une architecture de microservices, intégrant le framework Spring Boot ainsi que d'autres technologies Spring telles que Spring Data JPA, Spring Cloud et Spring Security, ainsi que des outils tels que Maven pour la gestion des dépendances. Ces technologies jouent un rôle crucial dans l'établissement de composants essentiels tels que Service Registry, API Gateway, etc.<br><br>
    De plus, ils nous permettent de développer des microservices indépendants tels que le service utilisateur pour la gestion des utilisateurs, le service de compte pour la génération de comptes et d'autres fonctionnalités associées, le service de transfert de fonds pour diverses opérations de transfert et le service de transaction pour visualiser les transactions et faciliter les retraits et dépôts. . Ces technologies non seulement rationalisent le développement, mais améliorent également l'évolutivité et la maintenabilité, garantissant ainsi un système bancaire robuste et efficace.
</p>

## 🏛️ Architecture

- **Service Registry:** Les microservices utilisent le service registry pour l'enregistrement et la découverte des services, ce qui aide les microservices à découvrir et à communiquer avec d'autres services, sans avoir besoin de coder en dur les points de terminaison lors de la communication avec d'autres microservices.

- **API Gateway:** Ce microservice est la passerelle API pour centraliser le point de terminaison de l'API, où tous les points de terminaison ont un point d'entrée commun à tous les points de terminaison. La passerelle API facilite également l'inclusion de la sécurité où l'autorisation et l'authentification de l'application.

- **Database per Microservice:** Chacun des microservices possède sa propre base de données dédiée. Ici, pour cette application pour tous les microservices, nous intégrons la base de données MySQL. Cela nous aide à isoler chacun des services les uns des autres, ce qui permet à chaque service d'avoir ses propres schémas de données et de faire évoluer chacune des bases de données si nécessaire.

<h2>🚀 Microservices</h2>

- **👤 User Service:** Le microservice utilisateur fournit des fonctionnalités de gestion des utilisateurs. Cela inclut l'enregistrement de l'utilisateur, la mise à jour des détails de l'utilisateur, l'affichage des informations sur l'utilisateur et l'accès à tous les comptes associés à l'utilisateur. De plus, ce microservice gère les processus d'authentification et d'autorisation des utilisateurs.

- **💼 Account Service:**Le microservice de compte gère les API liées au compte. Il permet aux utilisateurs de modifier les détails du compte, d'afficher tous les comptes liés au profil utilisateur, d'accéder à l'historique des transactions pour chaque compte et prend en charge le processus de fermeture de compte.

- **💸 Fund Transfer Service:** Le microservice de transfert de fonds facilite diverses fonctionnalités liées au transfert de fonds. Les utilisateurs peuvent lancer des transferts de fonds entre différents comptes, accéder aux enregistrements détaillés des transferts de fonds et afficher les détails spécifiques de toute transaction de transfert de fonds.

- **💳 Transactions Service:** Le service de transaction offre une gamme de services liés aux transactions. Les utilisateurs peuvent afficher les transactions basées sur des comptes spécifiques ou des identifiants de référence de transaction, ainsi qu'effectuer des dépôts ou des retraits de leurs comptes.
