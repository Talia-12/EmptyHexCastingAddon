package yourmod.name.here.common.casting.actions

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.getPlayer
import at.petrak.hexcasting.api.spell.iota.Iota

object OpExampleConstMediaAction : ConstMediaAction {
	/**
	 * The number of arguments from the stack that this action requires.
	 */
	override val argc = 1

	/**
	 * The method called when this Action is actually executed. Accepts the [args]
	 * that were on the stack (there will be [argc] of them), and the [ctx],
	 * which contains things like references to the caster, the ServerLevel,
	 * methods to determine whether locations and entities are in ambit, etc.
	 * Returns the list of iotas that should be added back to the stack as the
	 * result of this action executing.
	 */
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val otherEntity = args.getEntity(0, argc)
		val isAlly = otherEntity.isAlliedTo(ctx.caster)
		// all types that can be converted to an iota will have .asActionResult
		// defined for them to make them easy to return as the result of an Action.
		return isAlly.asActionResult
	}
}