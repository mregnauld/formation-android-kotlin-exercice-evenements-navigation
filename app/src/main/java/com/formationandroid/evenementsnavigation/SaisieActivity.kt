package com.formationandroid.evenementsnavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_saisie.*

class SaisieActivity : AppCompatActivity()
{

	override fun onCreate(savedInstanceState: Bundle?)
    {
        // init :
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_saisie)
	}

	/**
	 * Listener clic sur le bouton valider.
	 * @param view Vue cliquée (ici, le bouton valider)
	 */
	fun onClicValider(view: View)
	{
		// renvoi de la saisie vers MainActivity, qui va déclencher l'appel de MainActivity.onActivityResult() :
		val intent = Intent()
		intent.putExtra(MainActivity.EXTRA_RETOUR_SAISIE, saisie_texte.text.toString())
		setResult(RESULT_OK, intent)

		// fermeture de l'activité :
		finish()
	}

}