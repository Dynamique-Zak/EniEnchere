<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Login Title</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <!-- Eni Enchere style -->
        <link href="vendor/eench/eench-style.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>

    <body>
        <div class="eech-page-wrapper">
            <h2 class="eench-h-title">ENI Enchères</h2>

            <h3 class="text-center eench-h-subtitle">Créer un compte</h3>
            <form method="post" action="RegisterServlet">
                <!-- Input : Pseudo -->
                <div class="mb-3">
                    <label for="eech-pseudo" class="form-label">Pseudo</label>
                    <input type="text" id="eech-pseudo" name="pseudo" class="form-control">
                </div>
                <!-- ./Input : Pseudo -->

                <!-- Input : Nom -->
                <div class="mb-3">
                    <label for="eech-nom" class="form-label">Nom</label>
                    <input type="text" id="eech-nom" name="name" class="form-control">
                </div>
                <!-- ./Input : Nom -->

                <!-- Input : Prenom -->
                <div class="mb-3">
                    <label for="" class="form-label">Prenom</label>
                    <input type="text" name="prenom" class="form-control">
                </div>
                <!-- ./Input : Prenom -->

                <!-- Input : Email -->
                <div class="mb-3">
                    <label for="" class="form-label">Email</label>
                    <input type="text" name="email" class="form-control">
                </div>
                <!-- ./Input : Email -->

                <!-- Input : Telephone -->
                <div class="mb-3">
                    <label for="" class="form-label">Telephone</label>
                    <input type="text" name="phone" class="form-control">
                </div>
                <!-- ./Input : Telephone -->

                <!-- Input : Rue -->
                <div class="mb-3">
                    <label for="" class="form-label">Rue</label>
                    <input type="text" name="rue" class="form-control">
                </div>
                <!-- ./Input : Rue -->

                <!-- Input : Code postale -->
                <div class="mb-3">
                    <label for="" class="form-label">Code postale</label>
                    <input type="text" name="codePostale" class="form-control">
                </div>
                <!-- ./Input : Code postale -->

                <!-- Input : City -->
                <div class="mb-3">
                    <label for="eech-citycode" class="form-label">Ville</label>
                    <input type="text" id="eech-citycode" name="city" class="form-control">
                </div>
                <!-- ./Input : City Code -->

                <!-- Input : Mot de passe -->
                <div class="mb-3">
                    <label for="eech-password" class="form-label">Mot de passe</label>
                    <input type="password" id="eech-password" name="password" class="form-control">
                </div>
                <!-- ./Input : Mot de passe -->

                <!-- Input : Mot de passe confirmation -->
                <div class="mb-3">
                    <label for="eech-password-confirm" class="form-label">Mot de passe confirmation</label>
                    <input type="password" id="eech-password-confirm" name="password-confirm" class="form-control">
                </div>
                <!-- ./Input : Mot de passe confirmation -->

                <!-- Buttons  -->
                <div class="d-grid gap-2 d-flex">
                    <input type="submit" class="btn btn-primary col-6" value="Créer"></button>
                    <button type="button" class="btn btn-primary col-6">Annuler</button>
                </div>

            </form>
        </div>
    </body>

    </html>