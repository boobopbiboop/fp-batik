-- CreateTable
CREATE TABLE "kain" (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,
    "class" TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "origin" TEXT NOT NULL,
    "century" INTEGER NOT NULL,
    "type" TEXT NOT NULL,
    "meaning" TEXT NOT NULL,
    "pilosophy" TEXT NOT NULL,
    "dirUrl" TEXT NOT NULL,
    "history" TEXT NOT NULL,
    "createdAt" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP(3) NOT NULL,

    CONSTRAINT "kain_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "kain_name_key" ON "kain"("name");
