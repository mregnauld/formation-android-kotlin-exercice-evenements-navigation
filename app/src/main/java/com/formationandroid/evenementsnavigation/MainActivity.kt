package com.formationandroid.evenementsnavigation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

	companion object
	{
		// Constantes :
		const val EXTRA_RETOUR_SAISIE = "EXTRA_RETOUR_SAISIE"
		private const val CODE_SAISIE = 1
	}

	// Texte reçu de SaisieActivity, ou null si non encore pre_y :
	private var texte: String? = null


	override fun onCreate(savedInstanceState: Bundle?)
	{
		// init :
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	/**
	 * Listener clic sur le bouton lancer.
	 * @param view Vue cliquée (ici, le bouton lancer)
	 */
	fun onClicLancer(view: View)
	{
		if (texte == null)
		{
			// lancement de SaisieActivity, avec attente d'un retour :
			val intent = Intent(this, SaisieActivity::class.java)
			startActivityForResult(intent, CODE_SAISIE)

			// affichage du spinner :
			progressbar.visibility = View.VISIBLE
		}
		else
		{
			// partage du texte reçu :
			val intent = Intent(Intent.ACTION_SEND)
			intent.putExtra(Intent.EXTRA_TEXT, texte)
			intent.type = "text/plain"

			// demande et personnalisation de la boîte de dialogue :
			val chooser = Intent.createChooser(intent, getString(R.string.main_envoyer))

			// vérification et envoi :
			if (intent.resolveActivity(packageManager) != null)
			{
				startActivity(chooser)
			}
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
	{
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == CODE_SAISIE && resultCode == Activity.RESULT_OK && data != null)
		{
			// récupération de la saisie de SaisieActivity :
			texte = data.getStringExtra(EXTRA_RETOUR_SAISIE)

			// affichage :
			texte_final.text = texte
		}
	}

}