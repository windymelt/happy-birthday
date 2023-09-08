import $ivy.`de.tototec::de.tobiasroeser.mill.vcs.version::0.4.0`
import de.tobiasroeser.mill.vcs.version.VcsVersion

import mill._, scalalib._
import publish._

object happybirthday extends RootModule with ScalaModule with PublishModule {
  def scalaVersion = "3.3.0"
  def ivyDeps = Agg(
    ivy"com.lihaoyi::os-lib::0.9.1",
    ivy"com.github.nscala-time::nscala-time:2.32.0"
  )

  override def artifactName = "happy-birthday"
  override def sonatypeUri = "https://s01.oss.sonatype.org/service/local"
  override def sonatypeSnapshotUri =
    "https://s01.oss.sonatype.org/content/repositories/snapshots"
  def pomSettings = PomSettings(
    description = artifactName(),
    organization = "io.github.windymelt",
    url = "https://github.com/windymelt/happy-birthday",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("windymelt", "happy-birthday"),
    developers = Seq(
      Developer("windymelt", "windymelt", "https://github.com/windymelt")
    )
  )
  override def publishVersion: T[String] = VcsVersion.vcsState().format()
}
