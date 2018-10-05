# SYM_Labo1

Si l'image n'apparait pas sur la deuxième activité c'est qu'elle n'est pas push sur l'emulateur.

### Question 1
Comment organiser les textes pour obtenir une application multi-langues (français, allemand,
italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la
langue par défaut ou dans une langue supplémentaire ?

- Dans Res, on crée un dossier du style "values-fr" "fr" étant la langue choisie. A l'intérieur il faut créer une copie du fichier strings.xml et changer la traduction des textes. La langue est choisie automatiquement selon la langue du téléphone, si il manque une traduction ou qu'il n'en existe simplement pas. Il prend la langue définie dans le dossier value initial. (par défaut)

### Question 2
Dans l’exemple fourni, sur le dialogue pop-up, nous affichons l’icône 
android.R.drawable.ic_dialog_alert , disponible dans le SDK Android mais qui n’est pas très bien adapté visuellement à notre utilisation. Nous souhaitons la remplacer avec notre propre icône, veuillez indiquer comment procéder. Dans quel(s) dossier(s) devons-nous ajouter cette image ? Décrivez brièvement la logique derrière la gestion des ressources de type « image » sur Android.

- L'image doit être ajouté dans Res=>drawable, Les fichiers dans drawable sont indépendant du téléphone et de l'utilisateur. Si on souhaite quelque chose de personnalisé on ira chercher cette information sur le téléphone ( via android.R ou en allant dans les dossier).

### Question 3
Lorsque le login est réussi, vous êtes censé chaîner une autre Activity en utilisant un Intent. Si je presse le bouton "Back" de l'interface Android, que puis-je constater ? Comment faire pour que l'application se comporte de manière plus logique ? Veuillez discuter de la logique derrière les activités Android.

- Si on souhaite que la nouvelle activité revienne a la précédente il faut s'arrurer que l'activité initiale n'a pas de finish() car sinon les activité de peuvent pas s'empiler.

### Question 4
On pourrait imaginer une situation où cette seconde Activity fournit un résultat (par exemple l’IMEI ou une autre chaîne de caractères) que nous voudrions récupérer dans l'Activity de départ. Comment procéder ?

- Dans l'activité parent j'ai changé le ```startActivity(intent)``` par ```startActivityForResult(intent, requestCode);``` . Dans l'activité enfant, j'ai redéfinis l'action onBackPressed() pour qu'elle recrée un Intent avec mon resultat. Dans l'activité parent j'utilise : onActivityResult

### Question 5
Vous noterez que la méthode getDeviceId() du TelephonyManager, permettant d’obtenir l’IMEI du téléphone, est dépréciée depuis la version 26 de l’API. Veuillez discuter de ce que cela implique lors du développement et de présenter une façon d’en tenir compte avec un exemple de code.

- Si cette méthode est dépréciée il se peut que cela ne fonctionne plus dans un certain temps.
```
@RequiresApi(api = Build.VERSION_CODES.O)
 TelephonyManager tm = (TelephonyManager)
            getSystemService(this.TELEPHONY_SERVICE);
    String imei = tm.getImei();
```

### Question 6
Dans l’activité de login, en plaçant le téléphone (ou l’émulateur) en mode paysage (landscape), nous constatons que les 2 champs de saisie ainsi que le bouton s’étendent sur toute la largeur de l’écran. Veuillez réaliser un layout spécifique au mode paysage qui permet un affichage mieux adapté et indiquer comment faire pour qu’il soit utilisé automatiquement à l’exécution.

- Un peu comme avec les langue, android studio gère automatiquement les layout, il suffit de faire un dossier layout-land et il prendra automatiquement les fichiers de ce dossier lorsque qu'il est en landskape, si il possède rien il prendra celui par défaut. (de même pour les drawable avec drawable-land).

### Question 7
Le layout de l’interface utilisateur de l’activité de login qui vous a été fourni a été réalisé avec un LinearLayout à la racine. Nous vous demandons de réaliser un layout équivalent utilisant cette fois-ci un RelativeLayout.

- Fait avec authent-bis.xml

### Question 8
Implémenter dans votre code les méthodes onCreate(), onStart(), onResume(),
onPause(), onStop(), etc... qui marquent le cycle de vie d'une application Android, et tracez leur exécution dans le logcat. Décrivez brièvement à quelles occasions ces méthodes sont invoquées. Si vous aviez (par exemple) une connexion Bluetooth (ou des connexions bases de données, ou des capteurs activés) ouverte dans votre Activity, que faudrait-il peut-être faire, à votre avis (nous ne vous demandons pas de code ici) ?

1. On Create
2. On Start
3. On Resume (juste après)
4. On Pause lorsque je change d'activité
5. On Stop a la fin du changement d'activité
6. On Start lorsque je reviens sur l'activité Main
7. On Resume juste après le start.

L'utilité de ces méthode à surcharger est de démarrer des composants / outils comme par exemple le bluetooth au démarrage d'une actvité et la fermer lors du passage en pause ou de l'arrêt de l'application.

### Question 9
Facultatif – Question Bonus - S’il vous reste du temps, nous vous conseillons de le consacrer à
mettre en place la résolution des permissions au runtime.

- J'ai utilisé la librairie Dexter, qui apparement fonctionne chez d'autre (implémentation semblable) apparement j'ai un soucis sur mon simulateur à cause de la différence de version Android par rapport a celle utilisée pour programmer l'application.
Malgré cela l'application n'a pas l'autorisation d'accès à "l'external storage" et à l'état du téléphone.  Il faudrait que nous ayont la popup de demnade d'authorisation, mais elle ne s'affiche pas. Nous supposont que c'est causé par les différences de version d'android.

