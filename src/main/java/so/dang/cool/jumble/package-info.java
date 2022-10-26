/**
 * Collections for independently typed items.
 * <p>
 * These are special-case data collections, and require ahead-of-time knowledge
 * of how and where they will be used.
 * <p>
 * Some types are intended to be traversed "manually" and do <b>not</b>
 * implement the {@link java.util.Collection} or {@link java.lang.Iterable}
 * interfaces. These are cases where it's not possible to get individual types
 * into that kind of context. If you really really feel the need to operate in
 * that world, you may be better off with something that implements
 * {@code Collection<Object>}.
 * <p>
 * Some use cases considered:
 * <ul>
 *   <li>Storing virtual machine state.</li>
 *   <li>Undo/redo stacks of actions.</li>
 *   <li></li>
 * </ul>
 * <p>
 * The type signatures of these types become unwieldy fast. When possible these
 * types are best when they live in a single scope. Use of {@code var} definition
 * is encouraged.
 * <p>
 * Individual classes should document recommendations for function parameters,
 * fields, and return values.
 */
package so.dang.cool.jumble;
